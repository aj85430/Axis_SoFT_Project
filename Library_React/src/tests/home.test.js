import { shallow, configure } from 'enzyme';
import Home from '../components/home';
import { render, screen } from '@testing-library/react';
import Adapter from 'enzyme-adapter-react-16';

configure({adapter: new Adapter()});

test('home', () => {
    const component = shallow(<Home/>);
    const insideComponent = component.find('.bg')
    expect(insideComponent.length).toBe(1);
})

/*test('renders', () => {
    render(<Home/>);
    const linkElement = screen.getByText(/BlueStone Library/i);
    expect(linkElement).toBeInTheDocument();
  }); */

 


  /*describe('Paragraph', () => {
    it('should render children inside a p tag', () => {
      const wrapper = shallow(<Home>Your Own Library</Home>)
      const paragraph = wrapper.find('p')
      expect(paragraph).toHaveLength(3)
      expect(paragraph.text()).toEqual('Your Own Library')
    })
  })*/