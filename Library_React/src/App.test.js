import { render, screen } from '@testing-library/react';
import App from './App';
import Adapter from 'enzyme-adapter-react-16';
import { shallow, configure } from 'enzyme';

configure({adapter: new Adapter()});
/*test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});*/

test('class app test', () => {
  const component1 = shallow(<App/>)
  const component2 = component1.find('.App')
  expect(component2.length).toBe(1);
})
