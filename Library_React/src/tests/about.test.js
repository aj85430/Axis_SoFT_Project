import { shallow, configure } from 'enzyme';
import About from '../components/about';
import { render, screen } from '@testing-library/react';
import Adapter from 'enzyme-adapter-react-16';
configure({adapter: new Adapter()});

test('about', () => {
    const component = shallow(<About/>);
    const insideComponent = component.find('.about')
    expect(insideComponent.length).toBe(1);
})

test('renders', () => {
    render(<About/>);
    const linkElement = screen.getByText(/About Us/i);
    expect(linkElement).toBeInTheDocument();
  }); 
  
 